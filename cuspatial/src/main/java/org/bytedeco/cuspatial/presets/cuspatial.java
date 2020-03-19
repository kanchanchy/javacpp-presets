
package org.bytedeco.cuspatial.presets;

import org.bytedeco.javacpp.annotation.NoException;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;
import org.bytedeco.javacpp.Loader;

@Properties(
    value = {
        @Platform(include = "<types.hpp>", "coordinate_transform.hpp", "hausdorff.hpp", "haversine.hpp", "point_in_polygon.hpp", "query.hpp", "shapefile_readers.hpp", "soa_readers.hpp", "trajectory.hpp", "utility.hpp")
    },
    target = "org.bytedeco.cuspatial",
    global = "org.bytedeco.cuspatial.global.cuspatial"
)

@NoException
public class cuspatial implements InfoMapper {
    static { Loader.checkVersion("org.bytedeco", "cuspatial"); }

    public void map(InfoMap infoMap) {
        infoMap.put(new Info("__restrict__", "constexpr", "__device__", "inline", "__global__", "__shared__").cppTypes().annotations())
               .put(new Info("uint32_t", "int32_t").cast().valueTypes("int").pointerTypes("IntPointer"))
               .put(new Info("size_t").cast().valueTypes("long").pointerTypes("CLongPointer"))
               .put(new Info("unsigned int").cast().valueTypes("long").pointerTypes("LongPointer"));
    }
}
