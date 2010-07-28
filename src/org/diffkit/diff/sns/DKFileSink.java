/**
 * Copyright 2010 Joseph Panico
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.diffkit.diff.sns;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang.ClassUtils;

import org.diffkit.common.DKValidate;
import org.diffkit.diff.engine.DKContext;

/**
 * @author jpanico
 */
public class DKFileSink extends DKWriterSink {

   private final File _file;

   public DKFileSink(String filePath_) throws IOException {
      this(new File(filePath_));
   }

   private DKFileSink(File file_) throws IOException {
      DKValidate.notNull(file_);
      if (file_.exists())
         throw new IOException(String.format("already exists file->%s", file_));
      _file = file_;
   }

   @Override
   public Kind getKind() {
      return Kind.FILE;
   }

   public File getFile() {
      return _file;
   }

   @Override
   public void open(DKContext context_) throws IOException {
      this.init(new BufferedWriter(new FileWriter(_file)), this.getFormatter());
      super.open(context_);
   }

   public String toString() {
      return String.format("%s[%s]", ClassUtils.getShortClassName(this.getClass()),
         _file.getAbsolutePath());
   }

}
