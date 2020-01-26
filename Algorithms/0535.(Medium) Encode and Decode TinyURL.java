/****************************************************************************************************
535. Encode and Decode TinyURL

Difficulty: Medium

TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl 
and it returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service. 
There is no restriction on how your encode/decode algorithm should work. 
You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.

****************************************************************************************************/


public class Codec {

    private Map<Integer, String> map = new HashMap();
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        int encode = longUrl.hashCode();
        map.put(encode, longUrl);
        return "http://tinyurl.com/" + Integer.toString(encode);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String hash = shortUrl.replace("http://tinyurl.com/", "");
        int decode = Integer.parseInt(hash);
        return map.get(decode);
        
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));


