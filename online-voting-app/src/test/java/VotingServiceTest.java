import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VotingServiceTest {

    private VotingService votingService;

    @BeforeEach
    void setUp() {
        votingService = new VotingService();
    }

    @Test
    void testValidVote() {
        String result = votingService.castVote("user1", "CandidateA");
        assertEquals("SUCCESS: Vote cast for CandidateA", result);
    }

    @Test
    void testDuplicateVotePrevention() {
        votingService.castVote("user1", "CandidateA");
        String result = votingService.castVote("user1", "CandidateB");
        assertEquals("DUPLICATE: User has already voted", result);
    }

    @Test
    void testVoteCountIncreases() {
        votingService.castVote("user1", "CandidateA");
        votingService.castVote("user2", "CandidateA");
        assertEquals(2, votingService.getVoteCount("CandidateA"));
    }

    @Test
    void testEmptyUserIdRejected() {
        String result = votingService.castVote("", "CandidateA");
        assertTrue(result.startsWith("INVALID"));
    }

    @Test
    void testEmptyCandidateRejected() {
        String result = votingService.castVote("user1", "");
        assertTrue(result.startsWith("INVALID"));
    }

    @Test
    void testHasVotedTracking() {
        assertFalse(votingService.hasVoted("user1"));
        votingService.castVote("user1", "CandidateA");
        assertTrue(votingService.hasVoted("user1"));
    }

    @Test
    void testMultipleCandidatesIndependent() {
        votingService.castVote("user1", "CandidateA");
        votingService.castVote("user2", "CandidateB");
        votingService.castVote("user3", "CandidateA");
        assertEquals(2, votingService.getVoteCount("CandidateA"));
        assertEquals(1, votingService.getVoteCount("CandidateB"));
    }
}