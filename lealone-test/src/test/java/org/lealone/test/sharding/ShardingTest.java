/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lealone.test.sharding;

import org.junit.Test;
import org.lealone.test.sql.SqlTestBase;

public class ShardingTest extends SqlTestBase {
    public ShardingTest() {
        // super("ShardingTestDB");
    }

    @Test
    public void run() throws Exception {
        // DATABASE和TENANT是同义词，多租户时用TENANT更明确
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS ShardingTestDB RUN MODE client_server");
        stmt.executeUpdate("ALTER DATABASE ShardingTestDB RUN MODE sharding");
        stmt.executeUpdate("CREATE TENANT IF NOT EXISTS ShardingTestDB RUN MODE client_server");
        stmt.executeUpdate("ALTER TENANT ShardingTestDB RUN MODE sharding");

        stmt.executeUpdate("drop table IF EXISTS ShardingTest");
        stmt.executeUpdate("create table IF NOT EXISTS ShardingTest(f1 int SELECTIVITY 10, f2 int, f3 int)");

        stmt.executeUpdate("create index IF NOT EXISTS ShardingTest_i1 on ShardingTest(f1)");

        stmt.executeUpdate("insert into ShardingTest(f1, f2, f3) values(1,2,3)");
        stmt.executeUpdate("insert into ShardingTest(f1, f2, f3) values(5,2,3)");
        stmt.executeUpdate("insert into ShardingTest(f1, f2, f3) values(3,2,3)");
        stmt.executeUpdate("insert into ShardingTest(f1, f2, f3) values(8,2,3)");
        stmt.executeUpdate("insert into ShardingTest(f1, f2, f3) values(3,2,3)");
        stmt.executeUpdate("insert into ShardingTest(f1, f2, f3) values(8,2,3)");
        stmt.executeUpdate("insert into ShardingTest(f1, f2, f3) values(3,2,3)");
        stmt.executeUpdate("insert into ShardingTest(f1, f2, f3) values(8,2,3)");
        stmt.executeUpdate("insert into ShardingTest(f1, f2, f3) values(3,2,3)");
        stmt.executeUpdate("insert into ShardingTest(f1, f2, f3) values(8,2,3)");

        sql = "select distinct * from ShardingTest where f1 > 3";
        sql = "select distinct f1 from ShardingTest";
        printResultSet();
    }
}