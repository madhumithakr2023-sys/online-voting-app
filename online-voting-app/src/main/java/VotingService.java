import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VotingService {

    private Set<String> votedUsers = new HashSet<>();
    private Map<String, Integer> voteCounts = new HashMap<>();

    public String castVote(String userId, String candidate) {
        if (userId == null || userId.trim().isEmpty()) {
            return "INVALID: User ID cannot be empty";
        }
        if (candidate == null || candidate.trim().isEmpty()) {
            return "INVALID: Candidate cannot be empty";
        }
        if (votedUsers.contains(userId)) {
            return "DUPLICATE: User has already voted";
        }
        votedUsers.add(userId);
        voteCounts.put(candidate, voteCounts.getOrDefault(candidate, 0) + 1);
        return "SUCCESS: Vote cast for " + candidate;
    }

    public int getVoteCount(String candidate) {
        return voteCounts.getOrDefault(candidate, 0);
    }

    public boolean hasVoted(String userId) {
        return votedUsers.contains(userId);
    }

    public static void main(String[] args) {
        VotingService service = new VotingService();
        System.out.println("Online Voting System Started!");
        System.out.println(service.castVote("user1", "CandidateA"));
        System.out.println(service.castVote("user1", "CandidateA"));
        System.out.println(service.castVote("user2", "CandidateB"));
        System.out.println("CandidateA votes: " + service.getVoteCount("CandidateA"));
    }
}