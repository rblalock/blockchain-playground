package blockchain

open class Application {
	companion object {
		@JvmStatic fun main(args: Array<String>) {
			println("blockchain.Blockchain prototyping")

			val nodeID = "NODEID-987654321"
			val blockchain = Blockchain()

			// Simulate mining for block
			fun mine () {
				val lastBlock = blockchain.lastBlock!!
				val proof = blockchain.proofOfWork(lastBlock.proof)

				blockchain.newTransaction("0", nodeID, 1)

				val previousHash = blockchain.hash(lastBlock)
				val newBlock = blockchain.newBlock(proof, previousHash)

				print("\nNew Block Created\n")
				print(newBlock)
			}

			// Simulate 3 mining for block instances
			mine()
			mine()
			mine()

			print("\n\nInternal class info:\n")
			print("\nTransactions\n")
			print(blockchain.transactions)
			print("\nChain\n")
			print(blockchain.chain)
		}
	}
}