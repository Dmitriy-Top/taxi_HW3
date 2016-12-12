package Utilities;

import java.util.Iterator;

/**
 * Created by admin on 12.12.2016.
 */
public class LinkedOrderList<T> implements Iterable {
    private Point<T> lastPoint;
    private Point<T> firstPoint;


    public void add(T t) {
        if (firstPoint == null) {
            firstPoint = new Point<T>(t, null, null);
            lastPoint = firstPoint;
        } else if (firstPoint != null & lastPoint != null) {
            Point prevLastPoint = lastPoint;
            lastPoint = new Point<T>(t, null, prevLastPoint);
            prevLastPoint.setNextPoint(lastPoint);
        }

    }

    @Override
    public Iterator iterator() {
        return new OrderListIterator();
    }


    class Point<T> {
        private T data;
        private Point nextPoint;
        private Point prevPoint;

        private Point(T data, Point nextPoint, Point prevPoint) {
            this.data = data;
            this.nextPoint = nextPoint;
            this.prevPoint = prevPoint;
        }

        private T getData() {
            return data;
        }


        private Point getNextPoint() {
            return nextPoint;
        }

        private void setNextPoint(Point nextPoint) {
            this.nextPoint = nextPoint;
        }

        private Point getPrevPoint() {
            return prevPoint;
        }

        private void setPrevPoint(Point prevPoint) {
            this.prevPoint = prevPoint;
        }
    }

    private class OrderListIterator implements Iterator {
        Point nextIteratorPoint;
        Point nowIteratorPoint;

        @Override
        public boolean hasNext() {
            Boolean hasNext = true;
            if (nowIteratorPoint == null) hasNext = false;
                return hasNext;
        }

        @Override
        public Object next() {
            Point point = nowIteratorPoint;
            nowIteratorPoint = nextIteratorPoint;
            if (nowIteratorPoint!=null)nextIteratorPoint = nowIteratorPoint.getNextPoint();
            return point.getData();
        }

        @Override
        public void remove() {
            if (firstPoint != null & lastPoint != null) {
                Point prevLastPoint = lastPoint.getPrevPoint();
                prevLastPoint.setNextPoint(null);
                lastPoint = prevLastPoint;
            }

        }

        public OrderListIterator() {
            this.nowIteratorPoint = firstPoint;
            this.nextIteratorPoint = firstPoint.nextPoint;
        }
    }
}
