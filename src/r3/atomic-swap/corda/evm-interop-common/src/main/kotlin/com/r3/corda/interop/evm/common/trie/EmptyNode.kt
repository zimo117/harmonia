/*
 * Copyright 2023, R3 LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.r3.corda.interop.evm.common.trie

import org.web3j.rlp.RlpEncoder
import org.web3j.rlp.RlpString

/**
 * Represents an EmptyNode in a Patricia Trie.
 *
 * This class is a specific type of Node, with its encoded form
 * being the RLP (Recursive Length Prefix) encoding of an empty byte array.
 */
object EmptyNode : Node {
    /**
     * Returns the RLP-encoded form of the EmptyNode,
     * which is an empty byte array encoded in RLP.
     */
    override val encoded: ByteArray
        get() = RlpEncoder.encode(RlpString.create(ByteArray(0)))

    override fun put(key: NibbleArray, newValue: ByteArray): Node = LeafNode(key, newValue)

    override fun get(key: NibbleArray): ByteArray = ByteArray(0)

    override fun generateMerkleProof(key: NibbleArray, store: WriteableKeyValueStore): KeyValueStore {
        throw IllegalArgumentException("Key is not part of the trie")
    }

    override fun verifyMerkleProof(key: NibbleArray, expectedValue: ByteArray, proof: KeyValueStore): Boolean {
        throw IllegalArgumentException("Key is not part of the trie")
    }
}