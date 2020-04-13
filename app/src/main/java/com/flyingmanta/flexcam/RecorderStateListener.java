package com.flyingmanta.flexcam;

import java.util.List;

interface RecorderStateListener {
    void onMerged(List parts, String fullMovieName);
}
