package br.ufc.util;

import android.content.res.Resources;
import br.ufc.activity.R;
import br.ufc.model.ClientGameState;
import br.ufc.model.Player;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class PlayerItemizedOverlay extends ItemizedOverlay<OverlayItem> {

	private Resources resources;

	public PlayerItemizedOverlay(Resources resources) {
		super(boundCenterBottom(resources.getDrawable(R.drawable.mapa_engenheiro_1)));
		this.resources = resources;
	}

	public void accessPopulate() {
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		Player player = ClientGameState.players.get(ClientGameState.players.keySet().toArray()[i]);
		OverlayItem item = new OverlayItem(player.createLocationGeoPoint(), player.getNome(),  player.getNome()+" Snippet"); 
		if (player.getTipo() == Player.CANGACEIRO) {
			switch (player.getPapel()) {
				case Player.ENGENHEIRO:
					item.setMarker(boundCenterBottom(resources.getDrawable(R.drawable.mapa_engenheiro_1)));
					break;
				case Player.ESPIAO:
					item.setMarker(boundCenterBottom(resources.getDrawable(R.drawable.mapa_espiao_1)));
					break;
				case Player.MEDICO:
					item.setMarker(boundCenterBottom(resources.getDrawable(R.drawable.mapa_medico_1)));
					break;
				case Player.MUNICIADOR:
					item.setMarker(boundCenterBottom(resources.getDrawable(R.drawable.mapa_municiador_1)));
					break;
				default:
					break;
			}
		} else {
			switch (player.getPapel()) {
				case Player.ENGENHEIRO:
					item.setMarker(boundCenterBottom(resources.getDrawable(R.drawable.mapa_engenheiro_2)));
					break;
				case Player.ESPIAO:
					item.setMarker(boundCenterBottom(resources.getDrawable(R.drawable.mapa_espiao_2)));
					break;
				case Player.MEDICO:
					item.setMarker(boundCenterBottom(resources.getDrawable(R.drawable.mapa_medico_2)));
					break;
				case Player.MUNICIADOR:
					item.setMarker(boundCenterBottom(resources.getDrawable(R.drawable.mapa_municiador_2)));
					break;
				default:
					break;
			}
		}
		return item;
	}

	@Override
	public int size() {
		return ClientGameState.players.size();
	}

	public void invokePopulate() {
		populate();
	}

	@Override
	protected boolean onTap(int i) {


//		GeoPoint geoPoint;
//		Player player = (Player)ClientGameState.playersCangaceiros.keySet().toArray()[i];
//
//		if(type == Player.CANGACEIRO)
//			player = (Player)ClientGameState.playersCangaceiros.keySet().toArray()[i];			
//		else
//			player = (Player)ClientGameState.playersJaguncos.keySet().toArray()[i];			
//
//		geoPoint = ClientGameState.playersCangaceiros.get(player).createLocationGeoPoint();
//
//
//		OverlayItem item = new OverlayItem(geoPoint, player.getNome(),  player.getNome()+" Snippet");
//		AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.activity);
//		dialog.setTitle(item.getTitle());
//		dialog.setMessage(item.getSnippet());
//		dialog.show();
		return true;
	}
}