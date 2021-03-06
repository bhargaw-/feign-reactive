/**
 * Copyright 2018 The Feign Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package reactivefeign.cloud;

import org.junit.Before;
import reactivefeign.ReactiveFeignBuilder;
import reactivefeign.testcase.IcecreamServiceApi;

import static reactivefeign.cloud.AllFeaturesTest.setupServersList;
import static reactivefeign.cloud.BuilderUtils.TEST_CLIENT_FACTORY;

/**
 * @author Sergii Karpenko
 */
public class RequestInterceptorTest extends reactivefeign.RequestInterceptorTest {

    protected static String serviceName = "RequestInterceptorTest";

    @Before
    public void setUpServersList() {
        setupServersList(serviceName, wireMockRule.port());
    }

    @Override
    protected ReactiveFeignBuilder<IcecreamServiceApi> builder() {
        return BuilderUtils.<IcecreamServiceApi>cloudBuilderWithExecutionTimeoutDisabled()
                .enableLoadBalancer(TEST_CLIENT_FACTORY);
    }

    @Override
    protected IcecreamServiceApi target(ReactiveFeignBuilder<IcecreamServiceApi> builder){
        return builder.target(IcecreamServiceApi.class, serviceName, "http://" + serviceName);
    }
}
