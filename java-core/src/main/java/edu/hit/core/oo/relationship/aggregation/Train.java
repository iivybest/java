package edu.hit.core.oo.relationship.aggregation;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Train</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年7月27日 - 下午4:31:00
 */
public class Train {
    private List<Engine> engines;

    {
        this.engines = new ArrayList<Engine>();
    }

    public Train addEngine(Engine... e) {
        for (Engine engine : e) this.engines.add(engine);
        return this;
    }

    public void removeEngine(Engine e) {
        this.engines.remove(e);
    }

    public void start() {
        engines.stream().forEach(e -> e.start());
    }
}
