package Model;

public interface ExpencesObservable {
    void addObserver(ExpencesObserver observer);
    void removeObserver(ExpencesObserver observer);
    void notifyObservers();
}