package com.gonnect.querydsl.druid;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DruidQueryBuilder {
    private final String query = "SELECT %s FROM %s WHERE %s LIMIT 1";

    public DruidQuery createQuery(String domain, String select, String filter) {
        Node rootNode = new RSQLParser().parse(filter);
        String filterClause = rootNode.accept(new DruidRsqlVisitor(), new StringBuilder()).toString();

        //TODO add the parser for select part
        return new DruidQuery(String.format(query, select, "\""+domain+"\"", filterClause), null);


    }
}

