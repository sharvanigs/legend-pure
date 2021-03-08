// Copyright 2021 Goldman Sachs
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.finos.legend.pure.runtime.java.extension.store.relational.compiled.natives;

import org.eclipse.collections.api.list.ListIterable;
import org.finos.legend.pure.m3.navigation.Instance;
import org.finos.legend.pure.m3.navigation.M3Properties;
import org.finos.legend.pure.m3.navigation.ProcessorSupport;
import org.finos.legend.pure.m4.coreinstance.CoreInstance;
import org.finos.legend.pure.runtime.java.compiled.generation.ProcessorContext;
import org.finos.legend.pure.runtime.java.compiled.generation.processors.NativeFunctionProcessor;
import org.finos.legend.pure.runtime.java.compiled.generation.processors.natives.AbstractNative;
import org.finos.legend.pure.runtime.java.compiled.generation.processors.valuespecification.ValueSpecificationProcessor;

public class DropTempTable extends AbstractNative
{
    public DropTempTable()
    {
        super("dropTempTable_String_1__DatabaseConnection_1__Nil_0_");
    }

    @Override
    public String build(CoreInstance topLevelElement, CoreInstance functionExpression, ListIterable<String> transformedParams, ProcessorContext processorContext)
    {
        final ProcessorSupport processorSupport = processorContext.getSupport();
        final ListIterable<? extends CoreInstance> parametersValues = Instance.getValueForMetaPropertyToManyResolved(functionExpression, M3Properties.parametersValues, processorSupport);

        String tableName = ValueSpecificationProcessor.processValueSpecification(topLevelElement, parametersValues.get(0), processorContext);
        String connection = ValueSpecificationProcessor.processValueSpecification(topLevelElement, parametersValues.get(1), processorContext);

        String toSqlString = "\"drop table \"+" + tableName;

        return "org.finos.legend.pure.runtime.java.extension.store.relational.compiled.RelationalGen.dropTempTable(" + tableName + "," + toSqlString + "," + connection + ",0,0," + NativeFunctionProcessor.buildM4LineColumnSourceInformation(functionExpression.getSourceInformation()) + ",es)";
    }
}