package awesome.shizzle.urbandictionary.ui.view.decorator

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import awesome.shizzle.urbandictionary.R

class DefinitionItemDecorator(context: Context) : RecyclerView.ItemDecoration() {
    private val spaceHeight = context.resources.getDimension(R.dimen.list_item_margin).toInt()
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = spaceHeight
            }
            left = spaceHeight
            right = spaceHeight
            bottom = spaceHeight
        }
    }
}