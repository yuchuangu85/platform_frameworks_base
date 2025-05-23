/*
 * Copyright (C) 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.systemui.volume.panel.ui.viewmodel

import android.content.applicationContext
import com.android.systemui.broadcast.broadcastDispatcher
import com.android.systemui.dump.dumpManager
import com.android.systemui.kosmos.Kosmos
import com.android.systemui.kosmos.applicationCoroutineScope
import com.android.systemui.statusbar.policy.configurationController
import com.android.systemui.volume.panel.dagger.factory.volumePanelComponentFactory
import com.android.systemui.volume.panel.domain.VolumePanelStartable
import com.android.systemui.volume.panel.domain.interactor.volumePanelGlobalStateInteractor
import com.android.systemui.volume.shared.volumePanelLogger

var Kosmos.volumePanelStartables: Set<VolumePanelStartable> by Kosmos.Fixture { emptySet() }

var Kosmos.volumePanelViewModel: VolumePanelViewModel by
    Kosmos.Fixture { volumePanelViewModelFactory.create(applicationCoroutineScope) }

val Kosmos.volumePanelViewModelFactory: VolumePanelViewModel.Factory by
    Kosmos.Fixture {
        VolumePanelViewModel.Factory(
            applicationContext,
            volumePanelComponentFactory,
            configurationController,
            broadcastDispatcher,
            dumpManager,
            volumePanelLogger,
            volumePanelGlobalStateInteractor,
        )
    }
