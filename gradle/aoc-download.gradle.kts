import java.net.HttpURLConnection
import java.net.URI

val aocYear = 2024

fun downloadInput(day: Int, session: String, targetFile: File) {
    if (!targetFile.parentFile.exists()) targetFile.parentFile.mkdirs()
    val url = URI.create("https://adventofcode.com/$aocYear/day/$day/input").toURL()
    val conn = (url.openConnection() as HttpURLConnection).apply {
        requestMethod = "GET"
        setRequestProperty("Cookie", "session=$session")
        setRequestProperty(
            "User-Agent",
            "github.com/zooty/adventofcode2024 build-script (+https://adventofcode.com)"
        )
        connectTimeout = 10000
        readTimeout = 30000
    }
    conn.inputStream.use { input ->
        val bytes = input.readAllBytes()
        val text = bytes.toString(Charsets.UTF_8)
        val trimmed = text.replace(Regex("[\\r\\n]+$"), "")
        targetFile.writeText(trimmed, Charsets.UTF_8)
    }
}

val downloadInputs by tasks.registering {
    group = "aoc"
    description = "Downloads Advent of Code inputs for all Day implementations (Day*.java)."

    inputs.files(fileTree("src/main/java") { include("**/day*/Day*.java") })
    outputs.dir("src/main/resources")

    doLast {
        val refresh = (findProperty("aoc.refresh") as String?)?.equals("true", ignoreCase = true) == true

        val days = fileTree("src/main/java") {
            include("**/day*/Day*.java")
        }.files.mapNotNull { f ->
            val m = Regex("Day(\\d+)\\.java").find(f.name)
            m?.groupValues?.get(1)?.toInt()
        }.toSortedSet()

        if (days.isEmpty()) {
            logger.lifecycle("No Day implementations found.")
            return@doLast
        }

        val toDownload = days.filter { day ->
            val target = file("src/main/resources/day${day}.txt")
            refresh || !target.exists()
        }

        if (toDownload.isEmpty()) {
            logger.lifecycle("All input files already present. Nothing to download.")
            return@doLast
        }

        val session: String? = (findProperty("aoc.session") as String?)
            ?: System.getenv("AOC_SESSION")
        require(!session.isNullOrBlank()) {
            "Missing Advent of Code session. Provide -Paoc.session=... or set env AOC_SESSION, or create input files manually."
        }

        toDownload.forEach { day ->
            val target = file("src/main/resources/day${day}.txt")
            logger.lifecycle("Downloading input for day ${day}...")
            try {
                downloadInput(day, session, target)
                logger.lifecycle("Downloaded day $day -> ${target.path}")
            } catch (e: Exception) {
                throw GradleException("Failed to download input for day ${day}: ${e.message}", e)
            }
        }
    }
}

// Ensure inputs are downloaded before building
tasks.named("build") {
    dependsOn(downloadInputs)
}

// Ensure resource processing waits for inputs to be available
tasks.named("processResources") {
    dependsOn(downloadInputs)
}