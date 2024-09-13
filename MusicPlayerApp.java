import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

class Song {
    private int id;
    private String name;
    private String lyrics;
    private String singer;
    private String filePath;

    public Song(int id, String name, String lyrics, String singer, String filePath) {
        this.id = id;
        this.lyrics = lyrics;
        this.name = name;
        this.singer = singer;
        this.filePath = filePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSinger() {
        return singer;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Artist: " + singer + ", Lyrics: " + lyrics;
    }
}

class MusicPlayer {
    private ArrayList<Song> playList = new ArrayList<>();
    private int currentSongIndex = -1;
    private boolean isPlaying = false;
    private Player player;
    private Thread playThread;

    public void addSong(Song song) {
        playList.add(song);
        if (currentSongIndex == -1) {
            currentSongIndex = 0;
        }
    }

    public void playMusic() {
        if (currentSongIndex != -1 && !playList.isEmpty()) {
            try {
                if (player != null) {
                    player.close();
                }
                String filePath = playList.get(currentSongIndex).getFilePath().replace("\"", "");
                System.out.println("Playing file at: " + filePath); 
                FileInputStream fileInputStream = new FileInputStream(filePath);
                player = new Player(fileInputStream);

                playThread = new Thread(() -> {
                    try {
                        player.play();
                    } catch (JavaLayerException e) {
                        System.out.println("Error playing music: " + e.getMessage());
                    }
                });

                playThread.start();
                System.out.println("Playing " + playList.get(currentSongIndex).getName() + "...");
                isPlaying = true;
            } catch (Exception e) {
                System.out.println("Error playing music: " + e.getMessage());
            }
        } else {
            System.out.println("No songs to play");
        }
    }

    public void pauseMusic() {
        if (isPlaying) {
            player.close();
            System.out.println(playList.get(currentSongIndex).getName() + " paused");
            isPlaying = false;
        } else {
            System.out.println("No music is playing.");
        }
    }

    public void getDetails() {
        if (!playList.isEmpty() && currentSongIndex != -1) {
            System.out.println("Current details: " + playList.get(currentSongIndex).toString());
        } else {
            System.out.println("No song to play");
        }
    }

    public void setMusic(int id) {
        for (int i = 0; i < playList.size(); i++) {
            if (playList.get(i).getId() == id) {
                currentSongIndex = i;
                System.out.println("Song set to " + playList.get(i).getName());
                return;
            }
        }
        System.out.println("Song not found");
    }

    public void deleteMusic(int id) {
        for (int i = 0; i < playList.size(); i++) {
            if (playList.get(i).getId() == id) {
                playList.remove(i);
                if (currentSongIndex >= i) {
                    currentSongIndex--;
                }
                System.out.println("Song deleted");
                return;
            }
        }
        System.out.println("Song not found");
    }

    public void editMusic(int id, String newName, String newArtist, String newLyrics, String newFilePath) {
        for (Song song : playList) {
            if (song.getId() == id) {
                song.setName(newName);
                song.setLyrics(newLyrics);
                song.setSinger(newArtist);
                song.setFilePath(newFilePath);
                System.out.println("Song edited");
                return;
            }
        }
        System.out.println("Song not found");
    }
}

public class MusicPlayerApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MusicPlayer m = new MusicPlayer();
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Song");
            System.out.println("2. Play Music");
            System.out.println("3. Pause Music");
            System.out.println("4. Get Details");
            System.out.println("5. Set Music");
            System.out.println("6. Delete Music");
            System.out.println("8. Next Music");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter song id");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter song name");
                    String songname = sc.nextLine();
                    System.out.println("Enter singer name");
                    String singer = sc.nextLine();
                    System.out.println("Enter lyrics");
                    String lyrics = sc.nextLine();
                    System.out.println("Enter file path");
                    String filePath = sc.nextLine();
                    m.addSong(new Song(id, songname, lyrics, singer, filePath));
                    break;
                case 2:
                    m.playMusic();
                    break;
                case 3:
                    m.pauseMusic();
                    break;
                case 4:
                    m.getDetails();
                    break;
                case 5:
                    System.out.println("Enter song id to set music");
                    int setid = sc.nextInt();
                    m.setMusic(setid);
                    break;
                case 6:
                    System.out.println("Enter id for delete");
                    int deleteid = sc.nextInt();
                    m.deleteMusic(deleteid);
                    break;
                case 8:
                    break;
                case 9:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
