<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>Documento #${tipoRichiesta} - MotorsRent</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dettaglioRichiesta.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>

<body>

<div class="no-print">
    <jsp:include page="header.jsp"/>
    <div style="text-align: center; margin-top: 20px; margin-bottom: -40px;">
        <a href="dashboardCliente" style="color: #666; text-decoration: none; font-size: 0.9rem;">
            <i class="fa-solid fa-arrow-left"></i> Torna alla Dashboard
        </a>
    </div>
</div>

<div class="sheet-wrapper">
    <div class="detail-sheet">

        <div class="sheet-body">

            <header class="doc-header">
                <div class="brand-area">
                    <h1>MotorsRent</h1>
                    <p>Luxury & Commercial Mobility</p>
                </div>
                <div class="doc-info">
                    <div class="status-badge ${richiesta.stato}">${richiesta.stato}</div>
                    <div class="doc-title">${tipoRichiesta eq 'Preventivo' ? 'OFFERTA COMMERCIALE' : 'CONTRATTO DI LEASING'}</div>
                    <div class="doc-number">RIF: #<c:choose><c:when test="${tipoRichiesta == 'Preventivo'}">${richiesta.idPreventivo}</c:when><c:otherwise>${richiesta.idLeasing}</c:otherwise></c:choose> / <fmt:formatDate value="${richiesta.dataRichiesta}" pattern="yyyy"/></div>
                    <div style="font-size: 0.85rem; color: #888; margin-top: 5px;">Data: <fmt:formatDate value="${richiesta.dataRichiesta}" pattern="dd/MM/yyyy"/></div>
                </div>
            </header>

            <div class="info-grid">
                <div class="info-box">
                    <h3>Cliente / Intestatario</h3>
                    <div class="info-row"><span class="label">Nome</span> <span class="value">${richiesta.utente.nome} ${richiesta.utente.cognome}</span></div>
                    <div class="info-row"><span class="label">Email</span> <span class="value">${richiesta.utente.email}</span></div>
                    <div class="info-row"><span class="label">Telefono</span> <span class="value">${richiesta.utente.telefono}</span></div>
                    <div class="info-row"><span class="label">Cod. Cliente</span> <span class="value">MR-${richiesta.utente.idUtente}</span></div>
                </div>

                <div class="info-box">
                    <h3>Oggetto della Richiesta</h3>
                    <div class="car-card">
                        <img src="${richiesta.auto.immagine}" alt="Auto" class="car-img">
                        <div class="car-details">
                            <strong>${richiesta.auto.marca} ${richiesta.auto.modello}</strong>
                            <span>Anno ${richiesta.auto.anno}</span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="financial-section">
                <h3>Dettagli Economici</h3>

                <table class="financial-table">
                    <c:if test="${tipoRichiesta == 'Preventivo'}">
                        <thead>
                        <tr>
                            <th>Descrizione Voce</th>
                            <th style="text-align: right;">Importo (€)</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Prezzo Listino Veicolo</td>
                            <td class="amount"><fmt:formatNumber value="${richiesta.auto.prezzo}" minFractionDigits="2"/></td>
                        </tr>
                        <c:if test="${richiesta.prezzoProposto > 0}">
                            <tr>
                                <td>Sconto Commerciale Applicato</td>
                                <td class="amount" style="color: #c0392b;">- <fmt:formatNumber value="${richiesta.auto.prezzo - richiesta.prezzoProposto}" minFractionDigits="2"/></td>
                            </tr>
                            <tr class="total-row">
                                <td class="total-label">Totale Offerta</td>
                                <td class="amount"><fmt:formatNumber value="${richiesta.prezzoProposto}" minFractionDigits="2"/></td>
                            </tr>
                        </c:if>
                        <c:if test="${richiesta.prezzoProposto <= 0}">
                            <tr><td colspan="2" style="text-align:center; padding:30px; color:#999;"><em>In attesa di elaborazione...</em></td></tr>
                        </c:if>
                        </tbody>
                    </c:if>

                    <c:if test="${tipoRichiesta == 'Leasing'}">
                        <thead>
                        <tr>
                            <th>Parametro Contratto</th>
                            <th style="text-align: right;">Valore</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Durata</td>
                            <td class="amount">${richiesta.durataMesi} Mesi</td>
                        </tr>
                        <tr>
                            <td>Chilometraggio Annuo</td>
                            <td class="amount">${richiesta.kmAnnui} km</td>
                        </tr>
                        <tr>
                            <td>Anticipo Versato</td>
                            <td class="amount">€ <fmt:formatNumber value="${richiesta.anticipo}" minFractionDigits="2"/></td>
                        </tr>

                        <c:if test="${richiesta.rataMensile > 0}">
                            <tr style="background-color: #f4f6f7;">
                                <td style="font-weight: 700;">Rata Mensile</td>
                                <td class="amount" style="font-weight: 700;">€ <fmt:formatNumber value="${richiesta.rataMensile}" minFractionDigits="2"/></td>
                            </tr>
                            <tr class="total-row">
                                <td class="total-label">Costo Totale Leasing <span style="font-size:0.7rem; font-weight:400; color:#888;">(Rata × Durata)</span></td>
                                <td class="amount">€ <fmt:formatNumber value="${richiesta.rataMensile * richiesta.durataMesi}" minFractionDigits="2"/></td>
                            </tr>
                        </c:if>
                        <c:if test="${richiesta.rataMensile <= 0}">
                            <tr><td colspan="2" style="text-align:center; padding:30px; color:#999;"><em>Piano finanziario in elaborazione...</em></td></tr>
                        </c:if>
                        </tbody>
                    </c:if>
                </table>
            </div>

            <c:if test="${not empty richiesta.messaggioVenditore}">
                <div class="notes-container">
                    <strong>Nota Ufficiale:</strong><br>
                        ${richiesta.messaggioVenditore}
                </div>
            </c:if>

        </div> <div class="doc-footer">
        <div class="footer-company">MotorsRent S.r.l. &middot; Sede Legale: Via Roma 123, Milano (MI)</div>
        <div class="footer-legal">
            P.IVA 12345678901 &middot; Capitale Sociale € 100.000 i.v. &middot; Iscritta al Registro Imprese di Milano<br>
            Documento generato digitalmente. Valido ai fini di legge secondo le normative vigenti.
        </div>
    </div>

    </div>

    <div class="actions no-print">
        <button onclick="window.print()" class="btn-print">
            <i class="fa-solid fa-file-pdf"></i> Scarica PDF
        </button>
    </div>

</div>

</body>
</html>