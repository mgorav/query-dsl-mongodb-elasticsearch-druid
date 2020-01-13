package com.gonnect.querydsl.druid;

import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.OrNode;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;

import static com.gonnect.querydsl.druid.QueryDslOperator.getSimpleOperator;

/**
 * Druid Grammar
 * [ EXPLAIN PLAN FOR ]
 * [ WITH tableName [ ( column1, column2, ... ) ] AS ( query ) ]
 * SELECT [ ALL | DISTINCT ] { * | exprs }
 * FROM table
 * [ WHERE expr ]
 * [ GROUP BY exprs ]
 * [ HAVING expr ]
 * [ ORDER BY expr [ ASC | DESC ], expr [ ASC | DESC ], ... ]
 * [ LIMIT limit ]
 * [ UNION ALL <another query> ]
 */
public class DruidRsqlVisitor implements RSQLVisitor<StringBuilder, StringBuilder> {


    @Override
    public StringBuilder visit(AndNode andNode, StringBuilder filterClause) {
        filterClause.append(" (");
        filterClause = andNode.getChildren().get(0).accept(this, filterClause);
        if (andNode.getChildren().size() == 2) {
            filterClause.append(" and ");
            andNode.getChildren().get(1).accept(this, filterClause);
        }
        filterClause.append(")");
        return filterClause;
    }

    @Override
    public StringBuilder visit(OrNode orNode, StringBuilder filterClause) {

        filterClause.append("(");
        filterClause = orNode.getChildren().get(0).accept(this, filterClause);
        if (orNode.getChildren().size() == 2) {
            filterClause.append(" or ");
            orNode.getChildren().get(1).accept(this, filterClause);
        }
        filterClause.append(")");
        return filterClause;
    }

    @Override
    public StringBuilder visit(ComparisonNode comparisonNode, StringBuilder filterClause) {

        filterClause.append("(").append(comparisonNode.getSelector());
        QueryDslOperator operator = getSimpleOperator(comparisonNode.getOperator());
        if (operator == null) {
            throw new IllegalArgumentException("Operator " + comparisonNode.getOperator() + " not supported");
        }
        switch (operator) {
            case EQUAL: {
                filterClause.append(" = ");
                //In case of equals, it can only be one argument
                filterClause.append(getArgumentInCorrectFormat(comparisonNode.getArguments().get(0)));
                break;
            }
        }
        filterClause.append(")");
        return filterClause;
    }

    private String getArgumentInCorrectFormat(String argument) {

        //assuming all are strings for now
        return '\'' + argument + '\'';
    }
}

