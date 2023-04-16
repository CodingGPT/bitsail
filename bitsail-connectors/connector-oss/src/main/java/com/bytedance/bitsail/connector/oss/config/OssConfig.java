/*
 * Copyright 2022-2023 Bytedance Ltd. and/or its affiliates.
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

package com.bytedance.bitsail.connector.oss.config;

import com.bytedance.bitsail.common.configuration.BitSailConfiguration;
import com.bytedance.bitsail.connector.oss.exception.OssConnectorErrorCode;
import com.bytedance.bitsail.connector.oss.option.OssReaderOptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OssConfig implements Serializable {
  private String bucket;
  private String accessKey;
  private String accessSecret;
  private String endpoint;
  private ContentType contentType;
  private Boolean skipFirstLine;
  private String filePath;

  public OssConfig() {
  }

  public enum ContentType {
    CSV,
    JSON
  }

  public OssConfig(BitSailConfiguration jobConf) {
    this.bucket = jobConf.getNecessaryOption(OssReaderOptions.BUCKET, OssConnectorErrorCode.REQUIRED_VALUE);
    this.accessKey = jobConf.getNecessaryOption(OssReaderOptions.ACCESS_KEY, OssConnectorErrorCode.REQUIRED_VALUE);
    this.accessSecret = jobConf.getNecessaryOption(OssReaderOptions.ACCESS_SECRET, OssConnectorErrorCode.REQUIRED_VALUE);
    this.endpoint = jobConf.getNecessaryOption(OssReaderOptions.ENDPOINT, OssConnectorErrorCode.REQUIRED_VALUE);
    this.contentType = OssConfig.ContentType.valueOf(jobConf.getNecessaryOption(OssReaderOptions.CONTENT_TYPE, OssConnectorErrorCode.UNSUPPORTED_TYPE).toUpperCase());
    this.skipFirstLine = jobConf.get(OssReaderOptions.SKIP_FIRST_LINE);
    this.filePath = jobConf.get(OssReaderOptions.FILE_PATH);
  }
}