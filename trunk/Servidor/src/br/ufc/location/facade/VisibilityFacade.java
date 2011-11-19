package br.ufc.location.facade;

import java.util.List;

public class VisibilityFacade implements IVisibilityFacade{

	@Override
	public void addVisibilityType(Integer typeFocus, Integer typeView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearVisibilityType(Integer typeFocus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeVisibility(Integer typeFocus, Integer typeView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IMobileDevice> getVisibleDevice(IMobileDevice device, List<IMobileDevice> devices) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addVisibilityConditionDistance(Integer typeDeviceFocus,
			Integer typeDeviceView, int status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addVisibilityRule(Integer typeDeviceFocus,
			Integer typeDeviceView, int visibility) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addVisibilityRuleDistance(Integer typeDeviceFocus,
			Integer typeDeviceView, double distance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearVisibilityRules(Integer typeDeviceFocus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeVisibilityRule(Integer typeDeviceFocus,
			Integer typeDeviceView) {
		// TODO Auto-generated method stub
		
	}

}
