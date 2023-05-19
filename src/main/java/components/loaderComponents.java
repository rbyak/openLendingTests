package components;

public abstract class loaderComponents<T extends loaderComponents<T>> {
    public T get() throws Exception {
        return isAlreadyLoaded() ? (T) this : go();
    }

    public T go() throws Exception {
        load();
        return check();
    }

    public T check() throws Exception {
        if (!isLoaded()) {
            throw new Exception("The page failed to load!");
        }

        return (T) this;
    }

    protected abstract void load();


    protected abstract boolean isLoaded() throws Exception;


    public abstract boolean isAlreadyLoaded();
}
