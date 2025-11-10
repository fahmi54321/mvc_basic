package com.example.mvc.screens.common.fragmentframehelper;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public interface HierarchicalFragment {
    @Nullable
    Fragment getHierarchicalParentFragment();
}
