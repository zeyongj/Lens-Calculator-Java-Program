package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// The following class is modified from Dr.Fraser's class of DoorManager in his video of Model / View Separation whose URL is https://www.youtube.com/watch?v=XqeKtX8Aa94&feature=youtu.be&ab_channel=BrianFraser .
public class Lens_Manager implements Iterable<Lens> {
    private List<Lens> lenses = new ArrayList<>();

    // Support adding new lenses.
    public void add(Lens lens) {
        lenses.add(lens);
    }

    @Override
    public Iterator<Lens> iterator() {
        return lenses.iterator();
    }

    // For retrieving a specific lens by its index.
    public Lens getLensAtIndex (int index){
        return lenses.get(index);
    }
}
