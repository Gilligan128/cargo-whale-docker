package com.cargowhale.docker.client.containers.info.stats

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonSetter
import groovy.transform.Canonical

@Canonical
class ContainerNetworkStats {

    Long rxBytes
    Long rxDropped
    Long rxErrors
    Long rxPackets
    Long txBytes
    Long txDropped
    Long txErrors
    Long txPackets

    @JsonGetter("rxBytes")
    Long getRxBytes() {
        return rxBytes
    }

    @JsonSetter("rx_bytes")
    void setRxBytes(final Long rxBytes) {
        this.rxBytes = rxBytes
    }

    @JsonGetter("rxDropped")
    Long getRxDropped() {
        return rxDropped
    }

    @JsonSetter("rx_dropped")
    void setRxDropped(final Long rxDropped) {
        this.rxDropped = rxDropped
    }

    @JsonGetter("rxErrors")
    Long getRxErrors() {
        return rxErrors
    }

    @JsonSetter("rx_errors")
    void setRxErrors(final Long rxErrors) {
        this.rxErrors = rxErrors
    }

    @JsonGetter("rxPackets")
    Long getRxPackets() {
        return rxPackets
    }

    @JsonSetter("rx_packets")
    void setRxPackets(final Long rxPackets) {
        this.rxPackets = rxPackets
    }

    @JsonGetter("txBytes")
    Long getTxBytes() {
        return txBytes
    }

    @JsonSetter("tx_bytes")
    void setTxBytes(final Long txBytes) {
        this.txBytes = txBytes
    }

    @JsonGetter("txDropped")
    Long getTxDropped() {
        return txDropped
    }

    @JsonSetter("tx_dropped")
    void setTxDropped(final Long txDropped) {
        this.txDropped = txDropped
    }

    @JsonGetter("txErrors")
    Long getTxErrors() {
        return txErrors
    }

    @JsonSetter("tx_errors")
    void setTxErrors(final Long txErrors) {
        this.txErrors = txErrors
    }

    @JsonGetter("txPackets")
    Long getTxPackets() {
        return txPackets
    }

    @JsonSetter("tx_packets")
    void setTxPackets(final Long txPackets) {
        this.txPackets = txPackets
    }
}
