/*
 * Copyright (C) 2017 Samuel Audet
 *
 * Licensed either under the Apache License, Version 2.0, or (at your option)
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation (subject to the "Classpath" exception),
 * either version 2, or any later version (collectively, the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     http://www.gnu.org/licenses/
 *     http://www.gnu.org/software/classpath/license.html
 *
 * or as provided in the LICENSE.txt file that accompanied this code.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bytedeco.zlib.presets;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;
import org.bytedeco.javacpp.tools.*;

@Properties(
    value = {
        @Platform(include = "<zlib.h>", link = "z@.1#"),
        @Platform(value = "windows", link = "zlib#")
    },
    target = "org.bytedeco.zlib",
    global = "org.bytedeco.zlib.global.zlib"
)
public class zlib implements InfoMapper {
    static { Loader.checkVersion("org.bytedeco", "zlib"); }

    public void map(InfoMap infoMap) {
        infoMap.put(new Info("ZEXTERN", "ZEXPORT", "z_const", "zlib_version").cppTypes().annotations())
               .put(new Info("FAR").cppText("#define FAR"))
               .put(new Info("OF").cppText("#define OF(args) args"))
               .put(new Info("Z_ARG").cppText("#define Z_ARG(args) args"))
               .put(new Info("Byte", "Bytef", "charf").cast().valueTypes("byte").pointerTypes("BytePointer"))
               .put(new Info("uInt", "uIntf").cast().valueTypes("int").pointerTypes("IntPointer"))
               .put(new Info("uLong", "uLongf", "z_crc_t", "z_off_t", "z_size_t").cast().valueTypes("long").pointerTypes("CLongPointer"))
               .put(new Info("z_off64_t").cast().valueTypes("long").pointerTypes("LongPointer"))
               .put(new Info("voidp", "voidpc", "voidpf").valueTypes("Pointer"))
               .put(new Info("gzFile_s").pointerTypes("gzFile"))
               .put(new Info("gzFile").valueTypes("gzFile"))
               .put(new Info("Z_LARGE64", "!defined(ZLIB_INTERNAL) && defined(Z_WANT64)").define(false))
               .put(new Info("inflateGetDictionary", "gzopen_w", "gzvprintf").skip());
    }
}
