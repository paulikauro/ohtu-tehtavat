
package statistics.matcher;

import java.lang.reflect.Method;
import statistics.Player;

import static java.lang.Character.toUpperCase;

public class HasAtLeast implements Matcher {
    
    private final int value;
    private final String category;

    public HasAtLeast(int value, String category) {
        this.value = value;
        this.category = category;
    }

    private String getGetterName() {
        return "get" + toUpperCase(category.charAt(0)) + category.substring(1);
    }

    @Override
    public boolean matches(Player p) {
        try {                                    
            Method method = p.getClass().getMethod(getGetterName());
            int playersValue = (Integer) method.invoke(p);
            return playersValue>=value;
        } catch (Exception ex) {
            System.out.println(ex);
            throw new IllegalStateException(
                    "Player does not have field " + category
                    + " or corresponding getter method",
                    ex
            );
        }
        
    }    
    
}
