package de.svdragster.tankfever;

import de.svdragster.tankfever.entities.polygons.Point;

/**
 * Created by Sven on 11.02.2017.
 */
public class Geo {

	/*public static Point[] smoothLine(Point a, Point b, Point toSmooth, double radius) {
		double angle = Math.atan2(toSmooth.getY() - a.getY(), toSmooth.getX() - a.getX()) - Math.atan2(toSmooth.getY() - b.getY(), toSmooth.getX() - b.getX());
		double PC1, PC2;
		double segment = PC1 = PC2 = radius / Math.abs(Math.tan(angle / 2));
		double PP1 = Math.sqrt(Math.pow(toSmooth.getX() - a.getX(), 2) + Math.pow(toSmooth.getY() - a.getY(), 2));
		double PP2 = Math.sqrt((toSmooth.getX() - b.getX()) + (toSmooth.getY() - b.getY()));
		double min = Math.min(PP1, PP2);
		if (segment > min) {
			segment = min;
			radius = segment * Math.abs(Math.tan(angle / 2));
		}
		double PO = Math.sqrt(radius * radius + segment * segment);
		int pointsCount = (int)Math.Abs(sweepAngle * degreeFactor);
		int sign = Math.signum(sweepAngle);

		PointF[] points = new PointF[pointsCount];

		for (int i = 0; i < pointsCount; ++i)
		{
			var pointX =
					(float)(circlePoint.X
							+ Math.Cos(startAngle + sign * (double)i / degreeFactor)
							* radius);

			var pointY =
					(float)(circlePoint.Y
							+ Math.Sin(startAngle + sign * (double)i / degreeFactor)
							* radius);

			points[i] = new PointF(pointX, pointY);
		}
		return new Point(0, 0);
	}*/

	public static Point[] smoothLine(Point a, Point b, Point edge) {
		return null;
	}

}
