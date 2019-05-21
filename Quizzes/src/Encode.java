public class Encode
{
    public String encode(String longUrl)
    {
        int len = longUrl.length();
        char [] tiny = new char[6];
        String shortUrl;

        for(int i = 7; i < 12; i++)
        {
            tiny[i-7] = longUrl.charAt(i);
        }

        shortUrl = String.valueOf(tiny);
        return "https://tinyurl.com/" + shortUrl;
    }
}