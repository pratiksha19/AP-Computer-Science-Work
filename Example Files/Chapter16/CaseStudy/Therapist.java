/* Case Study: Therapist.java
1) This class emulates a nondirective psychotherapist.
2) The major method, reply, accepts user statements and generates
   a nondirective reply.
*/

import java.util.*;

public class Therapist{


   private Set<String> hedgeSet;             //The set of hedges
   private Set<String> qualifierSet;          //The set of qualifiers
   private Map<String, String> replacementMap;  //The map of 
                                                //replacement words

   public Therapist(){   
      hedgeSet = new HashSet<String>();
      hedgeSet.add("Please tell me more");
      hedgeSet.add("Many of my patients tell me the same thing");
      hedgeSet.add("It is getting late, maybe we had better quit");
      
      qualifierSet = new HashSet<String>();
      qualifierSet.add("Why do you say that ");
      qualifierSet.add("You seem to think that ");
      qualifierSet.add("So, you are concerned that ");
      
      replacementMap = new HashMap<String, String>();
      replacementMap.put("i", "you");
      replacementMap.put("me", "you");
      replacementMap.put("my", "your");
      replacementMap.put("am", "are");
   }

   public String reply(String patientString){
   //Replies to the patientŐs statement with either a hedge or 
   //a string consisting of a qualifier concatenated to
   //a transformed version of the patientŐs statement.
   //  Preconditions  -- none
   //  Postconditions -- returns a reply
   
      String reply = "";            //The therapistŐs reply
      int choice = randomInt(1, 3); //Generate a random number 
                                    //between 1 and 3
      
      //If the patient says nothing, then encourage him.
      if (patientString.trim().equals(""))
         return "Take your time. Some things are difficult to talk about.";   

      //Else reply with a hedge or a qualified response
      if(choice == 1)
         reply = hedge(hedgeSet);             //Hedge 1/3 of the time
      else if (choice == 2 || choice == 3)
         reply = qualifier(qualifierSet) +    //Build a qualified response
                 changePerson(patientString); //2/3 of the time
      return reply;
   }

   private String hedge(Set<String> hedgeSet){
   //Selects a hedge at random
   //  Preconditions  -- the hedge set has been initialized
   //  Postconditions -- returns a randomly selected hedge
   
      return selectRandom(hedgeSet);
   }

   private String qualifier(Set<String> qualifierSet){
   //Selects a qualifier at random
   //  Preconditions  -- the qualifier set has been initialized
   //  Postconditions -- returns a randomly selected qualifier
   
      return selectRandom(qualifierSet);
   }

   private String changePerson(String str){
   //Returns a string created by swapping i, me, etc. for you, your, etc.
   //in the string str
   //  Preconditions  -- none
   //  Postconditions -- returns the created string

      //Tokenize str
      StringTokenizer tokens = new StringTokenizer(str, " \n.,!;?");       
      String result = "";                     //Create a response string
      
      //Build the response from replacements of the tokens
      while (tokens.hasMoreTokens()){
         String keyWord = tokens.nextToken();
         String replacement = findReplacement(keyWord);
         result = result + replacement + " ";
      }
      return result;
   }

   private String findReplacement(String keyWord){
   //Returns the value associated with the keyword or the keyword itself
   //if the keyword is not in the map.
   //  Preconditions  -- the replacement map has been initialized
   //  Postconditions -- returns the replacement
   
      keyWord = keyWord.toLowerCase();
      if (replacementMap.containsKey(keyWord))
         return (String) replacementMap.get(keyWord);
      else
         return keyWord;
   }

   private String selectRandom(Set<String> set){
   //Selects an entry at random from the set
   //  Preconditions  -- the set is not empty
   //  Postconditions -- returns the random entry
   
      int index = randomInt(0, set.size() - 1);
      Iterator<String> iter = set.iterator();
      for (int i = 0; i < index; i++)
         iter.next();
      return iter.next();
   }

   private int randomInt(int low, int high){
   //Generate a random number between low and high
   //  Preconditions  -- low <= high
   //  Postconditions -- returns the random number
   
      return (int) (low + Math.random() * (high - low  + 1));
   }
}