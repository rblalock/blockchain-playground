package blockchain

import java.time.LocalDateTime
import java.util.*

data class BlockTransaction(
	val sender: String,
	val recipient: String,
	val amount: Int
)

data class Block(
	val index: Int,
	val timestamp: LocalDateTime,
	val transactions: ArrayList<BlockTransaction>,
	val proof: Int,
	val previousHash: String
)

class Blockchain {
	val chain = ArrayList<Block>()
	val transactions = ArrayList<BlockTransaction>()
	var lastBlock: Block? = null

	init {
		// Start off with an original block
		this.newBlock(100, "1")
	}

	/**
	 * Creates a new block in the chain
	 * @param proof Proof of new block
	 * @param previousHash Hash of previous block
	 * @return new block
	 */
	fun newBlock(proof: Int, previousHash: String?): Block {
		val pastHash = previousHash ?: this.hash(this.chain[-1])

		val block = Block(
				this.chain.count() + 1,
				LocalDateTime.now(),
				this.transactions,
				proof,
				pastHash
		)

		this.lastBlock = block
		this.chain.add(block)

		return block
	}

	/**
	 * Create a new transaction
	 * @param sender Address of sender
	 * @param recipient Address of recipient
	 * @param amount Amount of transaction
	 * @return last block
	 */
	fun newTransaction(sender: String, recipient: String, amount: Int): Int {
		this.transactions.add(BlockTransaction(sender, recipient, amount))

		return (this.lastBlock?.index ?: 0) + 1
	}

	/**
	 * 256 sha Hashes a block
	 * @param block The block to hash
	 * @return The sha256 encoded block string
	 */
	fun hash(block: Block): String {
		return HashUtils.sha256(block.toString())
	}

	/**
	 * Proof of work check / test
	 * @param lastProof
	 * @return the proof that passes
	 */
	fun proofOfWork(lastProof: Int): Int {
		var currentProof = 0

		while (this.validateProof(lastProof, currentProof) == false) {
			currentProof++
		}

		return currentProof
	}

	/**
	 * Validates the proof
	 * @param lastProof
	 * @param currentProof
	 * @return True if proof is correct
	 */
	fun validateProof(lastProof: Int, currentProof: Int): Boolean {
		val guess = (lastProof.toString() + currentProof.toString())
		val guessHash = HashUtils.sha256(guess)

		return guessHash.substring(0, 4) == "0000"
	}
}