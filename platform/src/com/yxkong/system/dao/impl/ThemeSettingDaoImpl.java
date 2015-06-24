package com.yxkong.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.yxkong.common.dao.impl.HibernateBaseDaoImpl;
import com.yxkong.system.dao.IThemeSettingDao;
import com.yxkong.system.model.ThemeSetting;
@Repository("themeSettingDao")
public class ThemeSettingDaoImpl extends HibernateBaseDaoImpl<ThemeSetting, String> implements IThemeSettingDao {



}
