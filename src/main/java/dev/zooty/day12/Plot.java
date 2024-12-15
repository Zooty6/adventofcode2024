package dev.zooty.day12;

import dev.zooty.day6.Position;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

@Getter
@Setter
public class Plot {
    private final char plant;

    private Plot upPlot;
    private Plot rightPlot;
    private Plot downPlot;
    private Plot leftPlot;

    public Plot(char plant) {
        this.plant = plant;
    }

    public void mapRegion(List<List<Plot>> plots, Position position) {
        if (upPlot == null) {
            mapNext(plots, position.up(), (Plot nextPlot) -> this.upPlot = nextPlot);
        }
        if (rightPlot == null) {
            mapNext(plots, position.right(), (Plot nextPlot) -> this.rightPlot = nextPlot);
        }
        if (downPlot == null) {
            mapNext(plots, position.down(), (Plot nextPlot) -> this.downPlot = nextPlot);
        }
        if (leftPlot == null) {
            mapNext(plots, position.left(), (Plot nextPlot) -> this.leftPlot = nextPlot);
        }
    }

    public int getAreaOfRegion(Set<Plot> calculatedPlots) {
        if (calculatedPlots.contains(this)) {
            return 0;
        }
        calculatedPlots.add(this);
        int price = 1;
        price += addAreaOfNeighbour(upPlot, calculatedPlots);
        price += addAreaOfNeighbour(rightPlot, calculatedPlots);
        price += addAreaOfNeighbour(downPlot, calculatedPlots);
        price += addAreaOfNeighbour(leftPlot, calculatedPlots);
        return price;
    }

    public int getPerimeterOfRegion(Set<Plot> calculatedPlots) {
        if (calculatedPlots.contains(this)) {
            return 0;
        }
        calculatedPlots.add(this);
        int price = 0;
        price += addPerimeterOfNeighbour(upPlot, calculatedPlots);
        price += addPerimeterOfNeighbour(rightPlot, calculatedPlots);
        price += addPerimeterOfNeighbour(downPlot, calculatedPlots);
        price += addPerimeterOfNeighbour(leftPlot, calculatedPlots);
        return price;
    }

    public int getSidesOfRegion(Set<Plot> calculatedPlots) {
        HashSet<Plot> calculatedUpSideFencePlots = new HashSet<>();
        HashSet<Plot> calculatedRightSideFencePlots = new HashSet<>();
        HashSet<Plot> calculatedDownSideFencePlots = new HashSet<>();
        HashSet<Plot> calculatedLeftSideFencePlots = new HashSet<>();

        return getSidesOfRegion(calculatedPlots,
                calculatedUpSideFencePlots,
                calculatedRightSideFencePlots,
                calculatedDownSideFencePlots,
                calculatedLeftSideFencePlots);
    }

    private int getSidesOfRegion(Set<Plot> calculatedPlots,
                                 Set<Plot> calculatedUpSideFencePlots,
                                 Set<Plot> calculatedRightSideFencePlots,
                                 Set<Plot> calculatedDownSideFencePlots,
                                 Set<Plot> calculatedLeftSideFencePlots) {
        if (calculatedPlots.contains(this)) {
            return 0;
        }
        calculatedPlots.add(this);

        int price = 0;
        price += addUpSideFence(calculatedUpSideFencePlots);
        price += addRightSideFence(calculatedRightSideFencePlots);
        price += addDownSideFence(calculatedDownSideFencePlots);
        price += addLeftSideFence(calculatedLeftSideFencePlots);

        if (upPlot != null && !calculatedPlots.contains(upPlot)) {
            price += upPlot.getSidesOfRegion(calculatedPlots,
                    calculatedUpSideFencePlots,
                    calculatedRightSideFencePlots,
                    calculatedDownSideFencePlots,
                    calculatedLeftSideFencePlots);
        }
        if (rightPlot != null && !calculatedPlots.contains(rightPlot)) {
            price += rightPlot.getSidesOfRegion(calculatedPlots,
                    calculatedUpSideFencePlots,
                    calculatedRightSideFencePlots,
                    calculatedDownSideFencePlots,
                    calculatedLeftSideFencePlots);
        }
        if (downPlot != null && !calculatedPlots.contains(downPlot)) {
            price += downPlot.getSidesOfRegion(calculatedPlots,
                    calculatedUpSideFencePlots,
                    calculatedRightSideFencePlots,
                    calculatedDownSideFencePlots,
                    calculatedLeftSideFencePlots);
        }
        if (leftPlot != null && !calculatedPlots.contains(leftPlot)) {
            price += leftPlot.getSidesOfRegion(calculatedPlots,
                    calculatedUpSideFencePlots,
                    calculatedRightSideFencePlots,
                    calculatedDownSideFencePlots,
                    calculatedLeftSideFencePlots);
        }
        return price;
    }

    private int addLeftSideFence(Set<Plot> calculatedSidePlots) {
        if (leftPlot != null || calculatedSidePlots.contains(this)) {
            return 0;
        }
        calculatedSidePlots.add(this);
        if (upPlot != null && !calculatedSidePlots.contains(upPlot)) {
            upPlot.addLeftSideFence(calculatedSidePlots);
        }
        if (downPlot != null && !calculatedSidePlots.contains(downPlot)) {
            downPlot.addLeftSideFence(calculatedSidePlots);
        }
        return 1;
    }

    private int addDownSideFence(Set<Plot> calculatedSidePlots) {
        if (downPlot != null || calculatedSidePlots.contains(this)) {
            return 0;
        }
        calculatedSidePlots.add(this);
        if (rightPlot != null && !calculatedSidePlots.contains(rightPlot)) {
            rightPlot.addDownSideFence(calculatedSidePlots);
        }
        if (leftPlot != null && !calculatedSidePlots.contains(leftPlot)) {
            leftPlot.addDownSideFence(calculatedSidePlots);
        }
        return 1;
    }

    private int addRightSideFence(Set<Plot> calculatedSidePlots) {
        if (rightPlot != null || calculatedSidePlots.contains(this)) {
            return 0;
        }
        calculatedSidePlots.add(this);
        if (upPlot != null && !calculatedSidePlots.contains(upPlot)) {
            upPlot.addRightSideFence(calculatedSidePlots);
        }
        if (downPlot != null && !calculatedSidePlots.contains(downPlot)) {
            downPlot.addRightSideFence(calculatedSidePlots);
        }
        return 1;
    }

    private int addUpSideFence(Set<Plot> calculatedSidePlots) {
        if (upPlot != null || calculatedSidePlots.contains(this)) {
            return 0;
        }
        calculatedSidePlots.add(this);
        if (rightPlot != null && !calculatedSidePlots.contains(rightPlot)) {
            rightPlot.addUpSideFence(calculatedSidePlots);
        }
        if (leftPlot != null && !calculatedSidePlots.contains(leftPlot)) {
            leftPlot.addUpSideFence(calculatedSidePlots);
        }
        return 1;
    }

    private int addPerimeterOfNeighbour(Plot nextPlot, Set<Plot> calculatedPlots) {
        if (nextPlot != null) {
            return nextPlot.getPerimeterOfRegion(calculatedPlots);
        }
        return 1;
    }

    private int addAreaOfNeighbour(Plot nextPlot, Set<Plot> calculatedPlots) {
        if (nextPlot != null) {
            return nextPlot.getAreaOfRegion(calculatedPlots);
        }
        return 0;
    }

    private void mapNext(List<List<Plot>> plots, Position nextPosition, Consumer<Plot> plotSetter) {
        if (inRegion(plots, nextPosition) && plots.get(nextPosition.x()).get(nextPosition.y()).getPlant() == this.plant) {
            Plot nextPlot = plots.get(nextPosition.x()).get(nextPosition.y());
            plotSetter.accept(nextPlot);
            nextPlot.mapRegion(plots, nextPosition);
        }
    }

    private boolean inRegion(List<List<Plot>> region, Position position) {
        return position.x() >= 0 && position.x() < region.size() && position.y() >= 0 && position.y() < region.getFirst().size();
    }
}
