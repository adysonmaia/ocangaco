package org.great.ocangaco;

import org.great.ocangaco.building.IBuilding;
import org.great.ocangaco.trap.ITrap;

public interface IPlay {
	void move();
	void shoot();
	void build(IBuilding building);
	void damage(ITrap trap);
}
