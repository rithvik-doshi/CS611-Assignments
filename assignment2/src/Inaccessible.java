/**
 * A space that cannot be accessed by any hero.
 */
public class Inaccessible extends Space{
    /**
     * Creates a new inaccessible space.
     */
    public Inaccessible(){
        super('X');
    }

    /**
     * Returns false, as this space cannot be accessed.
     * @param heroes The heroes to check.
     * @return False.
     */
    @Override
    public boolean tryAccess(DataList<Hero> heroes) {
        return false;
    }
}
