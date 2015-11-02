package miw.semantic.symboltable;

import miw.ast.statements.definitions.Definition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mvidalgarcia on 2/11/15.
 */
public class SymbolTable {
    public List<Map<String, Definition>> table;
    public Integer scope = 0;

    public SymbolTable() {
        table = new ArrayList<Map<String, Definition>>();
        table.add(new HashMap<String, Definition>()); // scope 0
    }

    public boolean insert(Definition definition) {
        if (table.get(scope).containsKey(definition.getName()))
            return false;
        table.get(scope).put(definition.getName(), definition);
        definition.setScope(scope);
        return true;
    }

    public Definition search(String name) {
        Definition definition = null;
        // Descendant scope search
        for(int i = scope; i >= 0 && definition == null; i--) {
            definition = table.get(i).get(name);
        }
        return definition;
    }

    public void set() {
        scope++;
        table.add(new HashMap<String, Definition>());
    }

    public void reset() {
        scope--;
        table.remove(scope);
    }

    @Override
    public String toString() {
        return "SymbolTable{" +
                "table=" + table +
                ", scope=" + scope +
                '}';
    }
}
