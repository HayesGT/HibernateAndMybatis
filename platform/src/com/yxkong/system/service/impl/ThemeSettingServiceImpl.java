package com.yxkong.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yxkong.common.dao.IHibernateBaseDao;
import com.yxkong.common.service.impl.HibernateBaseServiceImpl;
import com.yxkong.system.dao.IThemeSettingDao;
import com.yxkong.system.model.ThemeSetting;
import com.yxkong.system.service.IThemeSettingService;
@Service("themeSettingService")
public class ThemeSettingServiceImpl extends HibernateBaseServiceImpl<ThemeSetting, String>
		implements IThemeSettingService {
    @Resource(name="themeSettingDao")
    private IThemeSettingDao themeSettingDao;
	@Override
	public IHibernateBaseDao<ThemeSetting, String> getHibernateDao() {
		return themeSettingDao;
	}

}
