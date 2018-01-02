import blockchain.Blockchain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class BlockchainTest {
	lateinit var blockchain: Blockchain

	@Before
	fun setUp() {
		this.blockchain = Blockchain()
	}

	@Test
	fun newBlockTest() {
		val lastBlock = blockchain.lastBlock!!
		val previousHash = blockchain.hash(lastBlock)
		val block = blockchain.newBlock(0, previousHash)

		assertEquals(block.proof, 0)
	}

	@Test
	fun newTransactionTest() {

	}
}