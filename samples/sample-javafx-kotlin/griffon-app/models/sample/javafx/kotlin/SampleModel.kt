/*
 * Copyright 2008-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample.javafx.kotlin;

import griffon.core.artifact.GriffonModel
import griffon.metadata.ArtifactProviderFor
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonModel

@ArtifactProviderFor(GriffonModel::class)
class SampleModel : AbstractGriffonModel() {
    private var _input: StringProperty = SimpleStringProperty(this, "input", "")

    var input: String
        get() = _input.get()
        set(s) = _input.set(s)

    fun inputProperty() = _input

    private var _output: StringProperty = SimpleStringProperty(this, "output", "")

    var output: String
        get() = _output.get()
        set(s) = _output.set(s)

    fun outputProperty() = _output
}