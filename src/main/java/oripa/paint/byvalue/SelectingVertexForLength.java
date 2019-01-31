package oripa.paint.byvalue;

import java.awt.geom.Point2D.Double;

import oripa.ORIPA;
import oripa.geom.GeomUtil;
import oripa.paint.core.PaintContext;
import oripa.paint.core.PickingVertex;

public class SelectingVertexForLength extends PickingVertex{
	
	public SelectingVertexForLength(){
		super();
	}
	
	@Override
	protected void initialize() {
	}


	private boolean doingFirstAction = true;
	@Override
	protected boolean onAct(PaintContext context, Double currentPoint,
			boolean doSpecial) {
		
		context.setMissionCompleted(false);
		
		if(doingFirstAction){
			ORIPA.doc.cacheUndoInfo();
			doingFirstAction = false;
		}
		
		boolean result = super.onAct(context, currentPoint, doSpecial);
		
		if(result == true){
			if(context.getVertexCount() < 2){
				result = false;
			}
		}
		
		return result;
	}

	@Override
	public void onResult(PaintContext context) {

        double length = GeomUtil.distance(
        		context.getVertex(0), context.getVertex(1));

        ValueDB valDB = ValueDB.getInstance();
        valDB.setLength(length);
        valDB.notifyObservers();

//        Globals.subLineInputMode = Constants.SubLineInputMode.NONE;

		
        doingFirstAction = true;
        context.clear(false);

        context.setMissionCompleted(true);
	}

	
}
