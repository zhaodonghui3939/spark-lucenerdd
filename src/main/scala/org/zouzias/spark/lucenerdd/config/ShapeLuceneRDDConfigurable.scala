/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.zouzias.spark.lucenerdd.config

import org.locationtech.spatial4j.io.ShapeIO

trait ShapeLuceneRDDConfigurable extends LuceneRDDConfigurable {

  protected val getPrefixTreeMaxLevel: Int = {
    if (config.hasPath("lucenerdd.spatial.prefixtree.maxlevel")) {
      config.getInt("lucenerdd.spatial.prefixtree.maxlevel")
    }
    else 11
  }

  protected val getPrefixTreeName: String = {
    if (config.hasPath("lucenerdd.spatial.prefixtree.name")) {
      config.getString("lucenerdd.spatial.prefixtree.name")
    }
    else "geohash"  // Geohash tree by default
  }

  protected val getPrefixTreeMaxDistErr: Double = {
    if (config.hasPath("lucenerdd.spatial.prefixtree.maxDistErr")) {
      config.getDouble("lucenerdd.spatial.prefixtree.maxDistErr")
    }
    else 1D
  }

  protected val getLocationFieldName: String = {
    if (config.hasPath("lucenerdd.spatial.location.field.name")) {
      config.getString("lucenerdd.spatial.location.field.name")
    }
    else "__location__"
  }

  protected val getShapeFormat: String = {
    if (config.hasPath("lucenerdd.spatial.shape.io.format")) {
      val format = config.getString("lucenerdd.spatial.shape.io.format")
      val availableFormats = Array(ShapeIO.GeoJSON, ShapeIO.LEGACY, ShapeIO.POLY, ShapeIO.WKT)
      if (availableFormats.contains(format)) format else ShapeIO.WKT
    }
    else ShapeIO.WKT
  }
}

