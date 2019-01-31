package oripa.paint.creasepattern.tool;

import java.util.ArrayList;
import java.util.Collection;

import javax.vecmath.Vector2d;

import oripa.geom.GeomUtil;
import oripa.value.OriLine;

public class LineDivider {

	/**
	 * 
	 * @param line
	 * @param v
	 * @param creasePattern
	 * @param paperSize
	 * @return null if not need to divides
	 */
	public Collection<OriLine> divideLineInCollection(
			OriLine line, Vector2d v,
			Collection<OriLine> creasePattern, double paperSize) {
		ArrayList<OriLine> divided = new ArrayList<>(2);

		// Normally you don't want to add a vertex too close to the end of the line
		if (GeomUtil.distance(line.p0, v) < paperSize * 0.001
				|| GeomUtil.distance(line.p1, v) < paperSize * 0.001) {
			return null;
		}

		OriLine l0 = new OriLine(line.p0, v, line.typeVal);
		OriLine l1 = new OriLine(v, line.p1, line.typeVal);
//		creasePattern.remove(line);
//		creasePattern.add(l0);
//		creasePattern.add(l1);

		return divided;
	}

}
