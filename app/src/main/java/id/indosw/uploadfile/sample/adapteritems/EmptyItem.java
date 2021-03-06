package id.indosw.uploadfile.sample.adapteritems;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import net.gotev.recycleradapter.AdapterItem;

import butterknife.BindView;
import id.indosw.uploadfile.sample.R;
import id.indosw.uploadfile.sample.views.ButterKnifeViewHolder;

/**
 * @author Aleksandar Gotev
 */

public class EmptyItem extends AdapterItem<EmptyItem.Holder> {

    private @StringRes
    int text;

    public EmptyItem(@StringRes int textResource) {
        text = textResource;
    }

    @NonNull
    @Override
    public String diffingId() {
        return EmptyItem.class.getName();
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_empty;
    }

    @Override
    public void bind(boolean firstTime, @NonNull Holder holder) {
        Context ctx = holder.textView.getContext();
        holder.textView.setText(ctx.getString(text));
    }

    public static class Holder extends ButterKnifeViewHolder {

        @BindView(R.id.textView)
        TextView textView;

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
