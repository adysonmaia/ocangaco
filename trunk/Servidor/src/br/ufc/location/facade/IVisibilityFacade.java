package br.ufc.location.facade;

import java.util.List;

public interface IVisibilityFacade {
	void addVisibilityRule(Integer typeDeviceFocus, Integer typeDeviceView,
			int visibility);

	void addVisibilityRuleDistance(Integer typeDeviceFocus,
			Integer typeDeviceView, double distance);

	void addVisibilityConditionDistance(Integer typeDeviceFocus,
			Integer typeDeviceView, int status);

	void clearVisibilityRules(Integer typeDeviceFocus);

	void removeVisibilityRule(Integer typeDeviceFocus, Integer typeDeviceView);

	List<IMobileDevice> getVisibleDevice(IMobileDevice device,
			List<IMobileDevice> devices);

	void addVisibilityType(Integer typeDeviceFocus, Integer typeDeviceView);

	void clearVisibilityType(Integer typeDeviceFocus);

	void removeVisibility(Integer typeDeviceFocus, Integer typeDeviceView);

}
