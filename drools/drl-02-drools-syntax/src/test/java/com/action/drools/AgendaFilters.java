package com.action.drools;

import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;

/**
 * 测试用：按规则名过滤 agenda。
 */
public final class AgendaFilters {

    private AgendaFilters() {
    }

    public static AgendaFilter filterByPrefix(final String prefix) {
        return new AgendaFilter() {
            @Override
            public boolean accept(Match match) {
                return match.getRule().getName().startsWith(prefix);
            }
        };
    }

    public static AgendaFilter filterByRuleName(final String ruleName) {
        return new AgendaFilter() {
            @Override
            public boolean accept(Match match) {
                return ruleName.equals(match.getRule().getName());
            }
        };
    }
}
