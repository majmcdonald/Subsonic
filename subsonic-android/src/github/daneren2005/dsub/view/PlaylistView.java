/*
 This file is part of Subsonic.

 Subsonic is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 Subsonic is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with Subsonic.  If not, see <http://www.gnu.org/licenses/>.

 Copyright 2009 (C) Sindre Mehus
 */
package github.daneren2005.dsub.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import github.daneren2005.dsub.R;
import github.daneren2005.dsub.domain.Playlist;
import github.daneren2005.dsub.util.FileUtil;
import github.daneren2005.dsub.util.Util;
import java.io.File;

/**
 * Used to display albums in a {@code ListView}.
 *
 * @author Sindre Mehus
 */
public class PlaylistView extends UpdateView {
	private static final String TAG = PlaylistView.class.getSimpleName();
	
	private Playlist playlist;

    private TextView titleView;
	private ImageView moreButton;

    public PlaylistView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.playlist_list_item, this, true);

        titleView = (TextView) findViewById(R.id.playlist_name);
		moreButton = (ImageView) findViewById(R.id.playlist_more);
		moreButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				v.showContextMenu();
			}
		});
    }

    public void setPlaylist(Playlist playlist) {
    	this.playlist = playlist;
        
        titleView.setText(playlist.getName());
		update();
    }
	
	@Override
	protected void update() {
		File file = FileUtil.getPlaylistFile(playlist.getName());
		if(file.exists()) {
			moreButton.setImageResource(R.drawable.list_item_more_shaded);
		} else {
			moreButton.setImageResource(R.drawable.list_item_more);
		}
    }
}
