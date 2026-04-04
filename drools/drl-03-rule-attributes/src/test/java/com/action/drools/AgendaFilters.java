package com.action.drools;

import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;

/**
 * 测试用：按规则名前缀过滤 agenda，便于只执行某一组 DRL 中的规则。
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
}
