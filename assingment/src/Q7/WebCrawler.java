package Q7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class WebCrawler {
    // Set up the initial queue with the starting URLs
    private static BlockingQueue<String> urlQueue = new LinkedBlockingQueue<>();
    static {
        urlQueue.add("https://example.com");
    }

    // Set up the maximum number of threads to be used
    private static final int MAX_THREADS = 10;

    // Set up a HashSet to keep track of visited URLs
    private static HashSet<String> visitedUrls = new HashSet<>();

    public static void main(String[] args) throws InterruptedException {
        // Create the threads and start them
        Thread[] threads = new Thread[MAX_THREADS];
        for (int i = 0; i < MAX_THREADS; i++) {
            threads[i] = new Thread(new Crawler());
            threads[i].start();
        }

        // Wait for all tasks to be completed
        for (Thread thread : threads) {
            thread.join();
        }
    }

    private static class Crawler implements Runnable {
        public void run() {
            while (true) {
                try {
                    // Get the next URL from the queue
                    String currentUrl = urlQueue.take();

                    // Check if the URL has already been visited
                    if (visitedUrls.contains(currentUrl)) {
                        continue;
                    }

                    // Make a request to the URL
                    URL urlObj = new URL(currentUrl);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(urlObj.openStream()));

                    // Extract links from the response
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("href=")) {
                            int startIndex = line.indexOf("href=") + 6;
                            int endIndex = line.indexOf("\"", startIndex);
                            String link = line.substring(startIndex, endIndex);

                            // Convert relative URLs to absolute URLs
                            if (!link.startsWith("http")) {
                                link = urlObj.getProtocol() + "://" + urlObj.getHost() + link;
                            }

                            // Add the link to the queue if it hasn't been visited yet
                            if (!visitedUrls.contains(link)) {
                                urlQueue.put(link);
                            }
                        }
                    }

                    // Mark the URL as visited
                    visitedUrls.add(currentUrl);
                } catch (Exception ignore) {}
            }
        }
    }
}

