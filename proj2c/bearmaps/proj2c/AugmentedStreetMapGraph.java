package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.WeirdPointSet;
import bearmaps.proj2c.utils.MyTrieSet;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {

    private Map<Point,Long> pointToID;
    private List<Point> points;
    private Map<String,List<String>> cleanedNameToName;
    private MyTrieSet cleanedNameTire;
    private Map<String, Node> nameToNode;

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        // You might find it helpful to uncomment the line below:
        pointToID = new HashMap<>();
        points = new ArrayList<>();
        cleanedNameToName = new HashMap<>();
        cleanedNameTire = new MyTrieSet();
        nameToNode = new HashMap<>();
        List<Node> nodes = this.getNodes();
        for(Node n:nodes){
            long id = n.id();
            if(neighbors(id).size()!=0) {
                double x = n.lon();
                double y = n.lat();
                pointToID.put(new Point(x, y), id);
                points.add(new Point(x, y));
            }
            if (n.name() != null){
                String cleanedName = clean(n.name());
                if(cleanedNameToName.containsKey(cleanedName)){
                    cleanedNameToName.get(cleanedName).add(n.name());
                } else {
                    List<String> dirtyNames =new ArrayList<>();
                    dirtyNames.add(n.name());
                    cleanedNameToName.put(cleanedName,dirtyNames);
                }

                cleanedNameTire.add(cleanedName);
                nameToNode.put(n.name(),n);
            }
        }


    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        WeirdPointSet p = new WeirdPointSet(points);
        Point closestPoint = p.nearest(lon, lat);
        long closest = pointToID.get(closestPoint);
        return closest;
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        List<String> toReturn = new ArrayList<>();
        String cleanedPrefix = clean(prefix);
        List<String> cleanedNamesWithPrefix = cleanedNameTire.keysWithPrefix(cleanedPrefix);
        for(String cleanedName:cleanedNamesWithPrefix){
            List<String> originalNames = cleanedNameToName.get(cleanedName);
            for(String name:originalNames) {
                toReturn.add(name);
            }
        }
        return toReturn;
    }

    private String clean(String dirtyString){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < dirtyString.length();i++){
            char c = dirtyString.charAt(i);
            if(c>='a'&&c<='z'||c == ' '){
                sb.append(c);
            }
            if(c>='A'&&c<='Z'){
                sb.append((char) (c+32));
            }
        }
        String cleanString = sb.toString();
        return cleanString;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        String cleanedLocationName = clean(locationName);
        List<Map<String, Object>> toReturn = new ArrayList<>();
        if(cleanedNameToName.containsKey(cleanedLocationName)){
            List<String> locationsName = cleanedNameToName.get(cleanedLocationName);
            for(String name:locationsName){
                Node location = nameToNode.get(name);
                Map<String, Object> locations = new HashMap<>();
                locations.put("lat",location.lat());
                locations.put("lon",location.lon());
                locations.put("name",location.name());
                locations.put("id",location.id());
                toReturn.add(locations);
            }
        }
        return toReturn;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
