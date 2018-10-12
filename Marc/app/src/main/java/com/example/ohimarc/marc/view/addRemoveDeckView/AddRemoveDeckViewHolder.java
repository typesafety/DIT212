package com.example.ohimarc.marc.view.addRemoveDeckView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ohimarc.marc.R;
import com.example.ohimarc.marc.presenter.AddRemoveDeckPresenter;

/**
 * Author Victor Johansson (Vroxie on github)
 */
public class AddRemoveDeckViewHolder extends RecyclerView.ViewHolder implements AddRemoveDeckView {

    TextView titleTextView;

    public AddRemoveDeckViewHolder(View itemView, final AddRemoveDeckPresenter presenter) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.deck_Title);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deckClicked(getAdapterPosition());
            }
        });
    }

    /**
     * Sets the title of deck in the recyclerview
     * @param title the title of the deck
     */
    @Override
    public void setTitle(String title) {
        titleTextView.setText(title);
    }


}
