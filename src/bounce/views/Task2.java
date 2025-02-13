package bounce.views;

import bounce.*;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

/*
 * TODO Complete this class to have a functioning Bounce application
 */
public class Task2 extends Task1 implements ShapeModelListener {
    public Task2(ShapeModel model) {
        super(model);
    }

    /**
     * ShapeModelEvent:ShapeAdded, ShapeRemoved
     * Task2 class need only generate TreeModel events that describe a single addition or removal of
     * a Shape at a time.
     * Hence, the childIndices and children arrays that form part of the state of a TreeModelEvent
     * should always have a length of 1.
     *
     * @param event describes the way in which a particular ShapeModel object
     *              has changed.
     */
    @Override
    public void update(ShapeModelEvent event) {
        int[] childIndices = new int[1];
        Object[] children = new Object[1];
        childIndices[0] = event.index();
        children[0] = event.operand();
        ShapeModel shapeModel = event.source();

        if (event.eventType() == ShapeModelEvent.EventType.ShapeAdded) {
            TreeModelEvent treeModelEvent = new TreeModelEvent(shapeModel,
                    event.parent().path().toArray(), childIndices, children);
            for (TreeModelListener l : this._treeModelListenerList)
                l.treeNodesInserted(treeModelEvent);

        } else if (event.eventType() == ShapeModelEvent.EventType.ShapeRemoved) {
            TreeModelEvent treeModelEvent = new TreeModelEvent(shapeModel,
                    event.parent() == null ? null : event.parent().path().toArray(), childIndices, children);
            for (TreeModelListener l : this._treeModelListenerList)
                l.treeNodesRemoved(treeModelEvent);

        }

    }
}
