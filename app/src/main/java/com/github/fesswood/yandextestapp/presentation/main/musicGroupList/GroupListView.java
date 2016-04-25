package com.github.fesswood.yandextestapp.presentation.main.musicGroupList;

import com.github.fesswood.yandextestapp.domain.musicGroup.MusicGroup;
import com.github.fesswood.yandextestapp.presentation.common.BaseView;

import java.util.List;

/**
 * Created by fesswood on 20.04.16.
 */
public interface GroupListView extends BaseView {

     void fillAdapter(List<MusicGroup> musicGroups) ;
}
