package com.juniorgang.util

/**
 * A utility class that handles parsing of authentication headers
 */
class HeaderParser {

    /**
     * Gets the username
     * @param header request.getHeader(Authorization)
     * @return the username
     */
    static String getUsernameFromAuthHeader(String header){
    int indexOfColon = header.indexOf(":")
    return header.substring(6, indexOfColon)
}

    /**
     * Gets the password
     * @param header request.getHeader(Authorization)
     * @return the password
     */
    static String getPasswordFromAuthHeader(String header){
        int indexOfColon = header.indexOf(":")
        return header.substring(indexOfColon+1)
    }



}
